package annotation.database;

import com.sun.mirror.apt.AnnotationProcessor;
import com.sun.mirror.apt.AnnotationProcessorEnvironment;
import com.sun.mirror.apt.AnnotationProcessorFactory;
import com.sun.mirror.declaration.AnnotationTypeDeclaration;
import com.sun.mirror.declaration.ClassDeclaration;
import com.sun.mirror.declaration.FieldDeclaration;
import com.sun.mirror.declaration.TypeDeclaration;
import com.sun.mirror.util.SimpleDeclarationVisitor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

import static com.sun.mirror.util.DeclarationVisitors.NO_OP;
import static com.sun.mirror.util.DeclarationVisitors.getDeclarationScanner;

public class TableCreationProcessorFactory implements AnnotationProcessorFactory {
    @Override
    public Collection<String> supportedOptions() {
        return null;
    }

    @Override
    public Collection<String> supportedAnnotationTypes() {
        return Arrays.asList(
                "annotation.database.SQLInteger",
                "annotation.database.SQLString",
                "annotation.database.SQLLong",
                "annotation.database.DBTable",
                "annotation.database.Constraints",
                "java.lang.Override");
    }

    @Override
    public AnnotationProcessor getProcessorFor(Set<AnnotationTypeDeclaration> set, AnnotationProcessorEnvironment annotationProcessorEnvironment) {
        return new TableCreationProcessor(annotationProcessorEnvironment);
    }

    private static class TableCreationProcessor implements AnnotationProcessor {

        private final AnnotationProcessorEnvironment environment;
        private String sql = "";
        public TableCreationProcessor(AnnotationProcessorEnvironment annotationProcessorEnvironment) {
            this.environment = annotationProcessorEnvironment;
        }

        @Override
        public void process() {
            for (TypeDeclaration typeDeclaration : environment.getSpecifiedTypeDeclarations()) {
                typeDeclaration.accept(getDeclarationScanner(new TableCreationVisitor(), NO_OP));
                sql = sql.substring(0, sql.length() - 1) + ");";
                System.out.println("creation SQL is :\n" + sql);
                sql = "";
            }
        }

        private  class TableCreationVisitor extends SimpleDeclarationVisitor {

            @Override
            public void visitClassDeclaration(ClassDeclaration d) {
                DBTable dbTable = d.getAnnotation(DBTable.class);
                if (dbTable != null) {
                    sql += "CREATE TABLE ";
                    sql += (dbTable.name().length() < 1) ? d.getSimpleName().toUpperCase() : dbTable.name();
                    sql += " (";
                }
            }

            @Override
            public void visitFieldDeclaration(FieldDeclaration d) {
                String columnName = "";
                if (d.getAnnotation(SQLInteger.class) != null) {
                    SQLInteger sInt = d.getAnnotation(SQLInteger.class);
                    // Use field name if name not specified
                    if (sInt.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sInt.name();
                    sql += "\n    " + columnName + " INT" + getConstraints(sInt.constraints()) + ",";
                }
                if (d.getAnnotation(SQLString.class) != null) {
                    SQLString sString = d.getAnnotation(SQLString.class);
                    // Use field name if name not specified.
                    if (sString.name().length() < 1)
                        columnName = d.getSimpleName().toUpperCase();
                    else
                        columnName = sString.name();
                    sql += "\n    " + columnName + " VARCHAR(" + sString.value() + ")" + getConstraints(sString.constraints())
                            + ",";
                }
            }

            private String getConstraints(Constraints con) {
                String constraints = "";
                if (!con.allowNull())
                    constraints += " NOT NULL";
                if (con.primaryKey())
                    constraints += " PRIMARY KEY";
                if (con.unique())
                    constraints += " UNIQUE";
                return constraints;
            }
        }




    }

  


}
