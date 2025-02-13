//package com.nkemjika.example;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.PropertySource;
//import org.springframework.context.annotation.PropertySources;
//import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Service;
//
//@Service
////@PropertySource("classpath:custom.properties")
//@PropertySources({
//        @PropertySource("classpath:custom.properties"),
//        @PropertySource("classpath:custom-file-2.properties")
//})
//public class MyFirstService {
//
//    private final MyFirstClass myFirstClass;
//    private Environment environment;
//
//
//
//    @Value("${my.custom.property}")
//    private String customProperty;
//
//    @Value("${my.prop}")
//    private String customPropertyFromAnotherFile;
//
//    @Value("${my.prop.2}")
//    private String customPropertyFromAnotherFile2;
//
//    public MyFirstService(MyFirstClass myFirstClass) {
//        this.myFirstClass = myFirstClass;
//    }
//
//    public String tellAStory() {
//        return "the dependency is saying " + myFirstClass.sayHello();
//    }
//
//    public String getCustomPropertyFromAnotherFile() {
//        return customPropertyFromAnotherFile;
//    }
//
//    public String getCustomPropertyFromAnotherFile2() {
//        return customPropertyFromAnotherFile2;
//    }
//
//    public String getCustomProperty() {
//        return customProperty;
//    }
//
////    public String getJavaVersion() {
////        return  environment.getProperty(("java.version"));
////    }
////
////    public String getOsName() {
////        return  environment.getProperty(("os.name"));
////    }
////
////    public String readProp() {
////        return  environment.getProperty(("my.custom.property"));
////    }
////
////    @Autowired
////    public void setEnvironment(Environment environment) {
////        this.environment = environment;
////    }
//}
