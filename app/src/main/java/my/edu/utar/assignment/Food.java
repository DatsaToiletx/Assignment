package my.edu.utar.assignment;

public class Food {

    private String cook_time;
    private String foodID;
    private String image;
    private String ingred;
    private String name;
    private String prep_time;
    private String steps;

    public Food() {
    }

    public Food(String cook_time, String foodID, String image, String ingred, String name, String prep_time, String steps) {
        this.cook_time = cook_time;
        this.foodID = foodID;
        this.image = image;
        this.ingred = ingred;
        this.name = name;
        this.prep_time = prep_time;
        this.steps = steps;
    }

    public String getCook_time() {
        return cook_time;
    }

    public void setCook_time(String cook_time) {
        this.cook_time = cook_time;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIngred() {
        return ingred;
    }

    public void setIngred(String ingred) {
        this.ingred = ingred;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrep_time() {
        return prep_time;
    }

    public void setPrep_time(String prep_time) {
        this.prep_time = prep_time;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
