package ${package}.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class InfoController {

    @Autowired
    private BuildProperties buildProperties;

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/info",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public BuildProperties info() {
        return this.buildProperties;
    }

}
