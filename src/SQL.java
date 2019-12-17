import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
/**
 * @Target里可以指定它能被注在哪个地方，选项有METHOD \ TYPE \ FIELD等等
 * @Retention中可以选择存活的时期，选择RUNTIME（运行中）
 */
public @interface SQL {

    String value();

}
