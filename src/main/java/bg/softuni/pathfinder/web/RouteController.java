package bg.softuni.pathfinder.web;

import bg.softuni.pathfinder.model.CategoryType;
import bg.softuni.pathfinder.model.Level;
import bg.softuni.pathfinder.model.Route;
import bg.softuni.pathfinder.service.RouteService;
import bg.softuni.pathfinder.model.dto.RouteShortInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/*
 * Controller to handle all things route relates
 */

@Controller
public class RouteController {
    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    /*
     * Method to handle the listing of all routes.
     *
     * @return the list routes view
     */

    @GetMapping("/routes")
    public String viewRoutes(Model model) {
        List<RouteShortInfoDTO> routes = routeService.getAll();
        model.addAttribute("allRoutes", routes);

        return "routes";
    }

    @GetMapping("/add-route")
    public ModelAndView addRoute() {
        ModelAndView modelAndView = new ModelAndView("add-route");

        modelAndView.addObject("route", new RouteShortInfoDTO());
        modelAndView.addObject("levels", Level.values());
        modelAndView.addObject("categoryTypes", CategoryType.values());

        return modelAndView;
    }

    @GetMapping("/route-details/{routeId}")
    public ModelAndView viewRouteDetails(@PathVariable Long routeId) {
        ModelAndView modelAndView = new ModelAndView("route-details");
        Route route = routeService.getRoute(routeId);

        modelAndView.addObject("routeData", route);

        return modelAndView;
    }
}
