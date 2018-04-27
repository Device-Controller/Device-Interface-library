package vislab.no.ntnu.annotations.processors;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import vislab.no.ntnu.annotations.DeviceSPI;


@SuppressWarnings({"Since15"})
@SupportedSourceVersion(SourceVersion.RELEASE_8)
@SupportedAnnotationTypes("vislab.no.ntnu.annotations.DeviceSPI")
public class DeviceAnnotationProcessor extends AbstractProcessor {
    private static final String PATH = "META-INF/services/";
    private static final String CONTRACT = "vislab.no.ntnu.providers.Device";

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return AnnotationProccesor.proccess(processingEnv,roundEnv, DeviceSPI.class,CONTRACT,PATH);
    }
}
