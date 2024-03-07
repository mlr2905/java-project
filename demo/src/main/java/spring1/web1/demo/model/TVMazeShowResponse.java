package spring1.web1.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TVMazeShowResponse {

    @JsonProperty ("id")
    protected Long tvShowId;

    @JsonProperty ("url")
    protected String imageUrl;

    @JsonProperty ("name")
    protected String name;

    public TVMazeShowResponse() {}

    public TVMazeShowResponse(Long tvShowId, String imageUrl, String name) {
        this.tvShowId = tvShowId;
        this.imageUrl = imageUrl;
        this.name = name;
    }
}
