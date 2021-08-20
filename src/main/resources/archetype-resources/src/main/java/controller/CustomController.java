package ${package}.controller;

import lombok.extern.log4j.Log4j2;
import ${package}.entity.schema_name.TableName;
import ${package}.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class CustomController {

    @Autowired
    private CustomService service;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/value",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public TableName getValue() {
        return this.service.getCustomValue();
    }

}
