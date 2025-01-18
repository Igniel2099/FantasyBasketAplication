package org.example.fantasybasketaplication.information;

public class Player {
    private String name;
    private String position;
    private Double price;
    private String team;
    private String pathPhotoName;
    private String pathPhotoTeam;


    public Player(String name, String position, Double price, String team) {
        this.name = name;
        this.position = position;
        this.price = price;
        this.team = team;
    }

    public Player(String name, String position, Double price, String team, String pathPhotoName, String pathPhotoTeam) {
        this.name = name;
        this.position = position;
        this.price = price;
        this.team = team;
        this.pathPhotoName = pathPhotoName;
        this.pathPhotoTeam = pathPhotoTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }


    public String getPathPhotoName() {
        return pathPhotoName;
    }

    public void setPathPhotoName(String pathPhotoName) {
        this.pathPhotoName = pathPhotoName;
    }

    public String getPathPhotoTeam() {
        return pathPhotoTeam;
    }

    public void setPathPhotoTeam(String pathPhotoTeam) {
        this.pathPhotoTeam = pathPhotoTeam;
    }

    @Override
    public String toString() {
        return "Person [name=" + name + ", position=" + position + ", price=" + price + ", team=" + team + "]";
    }
}
