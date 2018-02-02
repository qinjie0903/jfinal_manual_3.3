#sql("findPrettyGirl")
	"select * from girl where age > ? and age < ? and weight <50"
#end


#sql("findPrettyGirlParaInt")
	"select * from girl where age > #para(0) and age < #para(1)"
#end

#sql("findPrettyGirlParaT")
	"select * from girl where age > #para(age) and weight < #para(weight)"
#end

#sql("search")
	"select * from article where title like concat('%',#para(title),'%')"
#end

### 定义模板函数 deleteByIdList

#define deleteByIdList(table,idList)
delete from #(table) where id in(
	#for(id:idList)
		#(for.index>0 ? "," ; "") #(id)
	#end
)
#end

### 调用上面定义的模板函数
#sql("deleteUsers")
	#@deleteByIdList("user",idList)
#end


