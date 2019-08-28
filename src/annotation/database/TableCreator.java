package annotation.database;

import annotation.practice.DBConnectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableCreator {

    public static void main(String[] args) throws ClassNotFoundException {
        List<String> strings = TableCreator.creatSqlString(args);
        Statement sqlSession = DBConnectUtil.getSqlSession("annotation/practice/DatabaseConfig.properties");
        try {
            sqlSession.execute(strings.get(0));
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (sqlSession != null){
                try {
                    sqlSession.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public static List<String> creatSqlString(String[] args) throws ClassNotFoundException {
        if(args.length < 1){
            System.out.println("arguments: annotated classes");
            System.exit(0);
        }
        List<String> result = new ArrayList<>();
        for (String className : args) {
            Class<?> cl = Class.forName(className);
            DBTable dbTable = cl.getAnnotation(DBTable.class);
            if(dbTable == null){
                System.out.println("No DBTable annotation in class" + className);
                continue;
            }
            String tableName = dbTable.name();
            if(tableName.length() < 1){
                tableName = cl.getName().toUpperCase();
            }
            List<String> columnList = new ArrayList<>();
            for (Field declaredField : cl.getDeclaredFields()) {
                String columnName = null;
                Annotation[] annotations = declaredField.getDeclaredAnnotations();
                if(annotations.length < 1){
                    continue;
                }
                if(annotations[0] instanceof SQLString){
                    SQLString sqlString = (SQLString) annotations[0];
                    if(sqlString.name().length() > 1){
                        columnName = sqlString.name();
                    }else{
                        columnName = declaredField.getName().toUpperCase();
                    }
                    columnList.add(columnName + " VARCHAR (" + sqlString.value() + ") " + getConstraints(sqlString.constraints()));
                }
                if(annotations[0] instanceof SQLInteger){
                    SQLInteger sqlInteger = (SQLInteger) annotations[0];
                    if(sqlInteger.name().length() > 1){
                        columnName = sqlInteger.name();
                    }else{
                        columnName = declaredField.getName().toUpperCase();
                    }
                    columnList.add(columnName + " INT " + getConstraints(sqlInteger.constraints()));
                }

            }
            StringBuilder sqlStringBuilder = new StringBuilder("CREATE TABLE ").append(tableName).append(" (");
            for (String s : columnList) {
                sqlStringBuilder.append("\n\t").append(s).append(" ,");
            }
            String  sql = sqlStringBuilder.substring(0,sqlStringBuilder.length() - 1)+");";
            System.out.println("Table Creation SQL for " + className
                    + " is :\n" + sql );
            result.add(sql);
        }
        return result;
    }

    private static String getConstraints(Constraints constraints) {
        String constraintsStr = "";
        if(constraints.allowNull()){
            constraintsStr += "NOT NULL ";
        }
        if (constraints.primaryKey()){
            constraintsStr += "PRIMARY KEY ";
        }
        if (constraints.unique()){
            constraintsStr += "UNIQUE ";
        }
        return constraintsStr;

    }

}
