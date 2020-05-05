package sample;

public class User {

    private int difficultyPoints;
    private int fighter;
    private int merchant;
    private int pilot;
    private int engineer;
    private int creditsValue;
    private String username;
    private Ship ship;
    public User() {
        ship = new Ship(5,
                (int) (Math.random() * 10) + 1, "Galactica",
                (int) (Math.random() * 10) + 1);
    }

    public Ship getShip() {
        return ship;
    }

    public String getDifficultyChoice() {
        return difficultyChoice;
    }

    public void setDifficultyChoice(String difficultyChoice) {
        this.difficultyChoice = difficultyChoice;
    }

    private String difficultyChoice;

    public int getDifficultyPoints() {
        return difficultyPoints;
    }

    public void setDifficultyPoints(int difficultyPoints) {
        this.difficultyPoints = difficultyPoints;
    }

    public int getFighter() {
        return fighter;
    }

    public void setFighter(int fighter) {
        this.fighter = fighter;
    }

    public int getMerchant() {
        return merchant;
    }

    public void setMerchant(int merchant) {
        this.merchant = merchant;
    }

    public int getPilot() {
        return pilot;
    }

    public void setPilot(int pilot) {
        this.pilot = pilot;
    }

    public int getEngineer() {
        return engineer;
    }

    public void setEngineer(int engineer) {
        this.engineer = engineer;
    }

    public int getCreditsValue() {
        return creditsValue;
    }

    public void setCreditsValue(int creditsValue) {
        this.creditsValue = creditsValue;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int skillPointSum() {
        return merchant + pilot + engineer + fighter;
    }

}
