package ${basePackage}.web${businessPackage};

import ${basePackage}.core.result.Result;
import ${basePackage}.core.result.ResultUtil;
import ${basePackage}.dto.common.IdVo;
import ${basePackage}.dto.common.PageVo;
import ${basePackage}.po${businessPackage}.${modelNameUpperCamel};
import ${basePackage}.service${businessPackage}.${modelNameUpperCamel}Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;


/**
 * ${modelNameUpperCamel}
 *
 * @version ${date}
 * @author ${author}
 */
@RestController
@RequestMapping("${baseRequestMapping}")
public class ${modelNameUpperCamel}Controller {

    @Resource
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;

    @PostMapping("/add")
    public Result add(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.save(${modelNameLowerCamel});
        return ResultUtil.success();
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody IdVo idVo) {
        ${modelNameLowerCamel}Service.deleteById(idVo.getId());
        return ResultUtil.success();
    }

    @PostMapping("/update")
    public Result update(@RequestBody ${modelNameUpperCamel} ${modelNameLowerCamel}) {
        ${modelNameLowerCamel}Service.update(${modelNameLowerCamel});
        return ResultUtil.success();
    }

    @PostMapping("/item")
    public Result item(@RequestBody IdVo idVo) {
        ${modelNameUpperCamel} ${modelNameLowerCamel} = ${modelNameLowerCamel}Service.findById(idVo.getId());
        return ResultUtil.success(${modelNameLowerCamel});
    }

    @PostMapping("/list")
    public Result list(@RequestBody PageVo pageVo) {
        PageHelper.startPage(pageVo.getPage(), pageVo.getSize());
        List<${modelNameUpperCamel}> list = ${modelNameLowerCamel}Service.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return ResultUtil.success(pageInfo);
    }
}
