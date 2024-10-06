package code.microsystem.ayush.contorller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/ayush")
public class AyushContorller {

    @GetMapping("/health")
    public String getHealth(){
    return "AyushPlus api running Healthy on dev with version 1.0";
}
}
