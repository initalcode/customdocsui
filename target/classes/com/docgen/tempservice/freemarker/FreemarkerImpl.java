package com.docgen.tempservice.freemarker;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.Version;


@Component
public class FreemarkerImpl {


		private	Configuration config = new Configuration();
		
		public FreemarkerImpl() throws IOException {
			
			config.setDirectoryForTemplateLoading(new File("G:/software-projects/customdocs/src/main/webapp/WEB-INF"));
			config.setObjectWrapper(new DefaultObjectWrapper());
			config.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			config.setDefaultEncoding("UTF-8");
			config.setIncompatibleImprovements(new Version(2,3,20));
		}
	
		public File process(String templateName, Object data) throws TemplateException{
			
			try {
				Template template = config.getTemplate(templateName);
				File file = new File("appealsLetter.html");
				Writer fileWriter = new FileWriter (file);
				template.process(data, fileWriter);
				fileWriter.close();
				return file;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
			
			
		}
	

}
