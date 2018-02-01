package demo;

import com.jfinal.core.Controller;

public class ProjectController extends Controller{
	
	//action带形参(java8)
	public void save(Project project) {
		project.save();
		render("index.html");
	}
}
