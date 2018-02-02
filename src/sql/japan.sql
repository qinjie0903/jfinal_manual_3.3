#namespace("japan")

	#sql("findPrettyGirl")
		"select * from girl where age > ? and age < ? and weight <50"
	#end
	
	
	#sql("find")
		select * from girl
		#for(x:cond)
			#(for.index==0 ? "where" : "and") #(x.key) #para(x.value)
		#end
	#end
	
#end
