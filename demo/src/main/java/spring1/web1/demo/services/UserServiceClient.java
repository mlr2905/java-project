package spring1.web1.demo.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring1.web1.demo.model.TVMazeShowResponse;

@FeignClient(name = "tvMazeService", url = "${tvmaze.url}") // Replace with your actual API URL
public interface UserServiceClient {

    @GetMapping("/shows/{tvShowId}")
    TVMazeShowResponse getShow(@PathVariable Integer tvShowId);
}
