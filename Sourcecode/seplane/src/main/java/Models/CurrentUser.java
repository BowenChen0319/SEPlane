package Models;

public class CurrentUser extends Benutzer {
    private static Benutzer current;

    public void setCurrent(Benutzer be){
        CurrentUser.current=be;
    }

    public Benutzer getCurrent(){
        return current;
    }
}

