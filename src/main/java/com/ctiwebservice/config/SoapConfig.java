package com.ctiwebservice.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class SoapConfig extends WsConfigurerAdapter {

  @Bean
  public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean<MessageDispatcherServlet>(servlet, "/service/*");
  }
  
  @Bean
  public FilterRegistrationBean<CorsFilter> corsFilterRegistration() {
      UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
      CorsConfiguration config = new CorsConfiguration();
      config.addAllowedOrigin("*");
      config.addAllowedMethod("POST");
      config.addAllowedHeader("*");
      config.setAllowCredentials(false);
      source.registerCorsConfiguration("/**", config);
      FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(source));
      bean.setOrder(0);
      return bean;
  }

  @Bean(name = "studentDetailsWsdl")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema studentSchema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("StudentDetailsPort");
    wsdl11Definition.setLocationUri("/service/student-details");
    wsdl11Definition.setTargetNamespace("http://www.ctiwebservice.com/xml/school");
    wsdl11Definition.setSchema(studentSchema);
    return wsdl11Definition;
  }
  
  @Bean(name = "productsWsdl")
  public DefaultWsdl11Definition productsWsdl11Definition(XsdSchema productsSchema) {
      DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
      wsdl11Definition.setPortTypeName("ProductsPort");
      wsdl11Definition.setLocationUri("/service/products");
      wsdl11Definition.setTargetNamespace("http://www.ctiwebservice.com/xml/school");
      wsdl11Definition.setSchema(productsSchema);
      return wsdl11Definition;
  }
  
  @Bean(name = "categoriesWsdl")
  public DefaultWsdl11Definition categoriesWsdl11Definition(XsdSchema categoriesSchema) {
      DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
      wsdl11Definition.setPortTypeName("CategoriesPort");
      wsdl11Definition.setLocationUri("/service/categories");
      wsdl11Definition.setTargetNamespace("http://www.ctiwebservice.com/xml/school");
      wsdl11Definition.setSchema(categoriesSchema);
      return wsdl11Definition;
  }

  @Bean
  public XsdSchema studentSchema() {
    return new SimpleXsdSchema(new ClassPathResource("student.xsd"));
  }
  
  @Bean
  public XsdSchema productsSchema() {
      return new SimpleXsdSchema(new ClassPathResource("student.xsd")); // Use the same XSD if it contains both definitions
  }
  
  @Bean
  public XsdSchema categoriesSchema() {
      return new SimpleXsdSchema(new ClassPathResource("student.xsd")); // Use the same XSD if it contains both definitions
  }
}