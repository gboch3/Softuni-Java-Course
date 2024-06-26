package bg.softuni.pathfinder.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryMovementController {

    @GetMapping("/pedestrian")
    public ModelAndView viewPedestrian() {
        return new ModelAndView("pedestrian");
    }

    @GetMapping("/bicycle")
    public ModelAndView viewBicycle() {
        return new ModelAndView("bicycle");
    }

    @GetMapping("/motorcycle")
    public ModelAndView viewMotorcycle() {
        return new ModelAndView("motorcycle");
    }

    @GetMapping("/car")
    public ModelAndView viewCar() {
        return new ModelAndView("car");
    }
}
