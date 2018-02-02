package demo;

import javax.sql.DataSource;

import com.jfinal.kit.PathKit;
import com.jfinal.plugin.activerecord.generator.Generator;

public class GenerateModel {

	public static void main(String[] args) {
		DataSource dataSource = null;
		
		// base model 使用的包名
		String baseModelPkg = "model.base";
		
		// base model 文件保存路径
		String baseModelDir = PathKit.getWebRootPath()+"/../src/model/base";
		
		// model 使用的包名
		String modelPkg = "model";
		
		// model文件保存路径
		String modelDir = baseModelDir+"/..";
		
		Generator generator = new Generator(dataSource, baseModelPkg,baseModelDir,modelPkg,modelDir);
		
		generator.generate();
	}
}
