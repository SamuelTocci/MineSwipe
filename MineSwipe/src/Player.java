public class Player {
    private Map map;

    public Player(Map map){
        this.map = map;
    }

    /*
    * checkt of positie binnen speelveld ligt
    * */
    public boolean valid(int xPos, int yPos){
        if(0<(xPos&yPos)  && yPos<map.getSizeY() && xPos<map.getSizeX()){
            return true;
        }
        return false;
    }
    /*
    * checkt of er een bom zit op deze plaats zit
    * */
    public boolean bCheck(int xPos, int yPos){
        Boolean flag= false;
        if(valid(xPos,yPos)){
            if(map.check(xPos, yPos)=="B"){
                flag = true;
            }
        }
        return flag;
    }

}
