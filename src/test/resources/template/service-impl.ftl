package ${basePackage}.service.impl${businessPackage};

import ${basePackage}.dao${businessPackage}.${modelNameUpperCamel}Mapper;
import ${basePackage}.po${businessPackage}.${modelNameUpperCamel};
import ${basePackage}.service${businessPackage}.${modelNameUpperCamel}Service;
import ${basePackage}.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * ${modelNameUpperCamel}
 *
 * @version ${date}
 * @author ${author}
 */
@Service
@Transactional
public class ${modelNameUpperCamel}ServiceImpl extends AbstractService<${modelNameUpperCamel}> implements ${modelNameUpperCamel}Service {

    @Resource
    private ${modelNameUpperCamel}Mapper ${modelNameLowerCamel}Mapper;

}
