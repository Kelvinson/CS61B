import sun.util.resources.cldr.fo.CalendarData_fo_FO;

public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p){
    xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
         mass = p.mass;
        imgFileName = p.imgFileName;

    }
    public double calcForceExertedBy(Planet p){
        return 6.67*Math.pow(10,-11)*mass*p.mass/(calcDistance(p)*calcDistance(p));
    }

    public double calcForceExertedByX(Planet p){
       return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
    }
    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] p){
        double net = 0;
        for(Planet pl : p){
            if(pl.equals(this)){
                continue;
            }
            net+= calcForceExertedByX(pl);
        }
        return net;

    }
    public double calcNetForceExertedByY(Planet[] p){

        double net = 0;
        for(Planet pl : p){
            if(pl.equals(this)){
                continue;
            }
            net+= calcForceExertedByY(pl);
        }
        return net;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);
    }
    public double calcDistance(Planet p){
       return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos)+(yyPos-p.yyPos)*(yyPos-p.yyPos));
    }
    public void update(double t,double fX,double fY){
       double accX,accY,velX,velY,posX,posY;
        accX = fX/mass;
        accY = fY/mass;
         xxVel += t*accX;
        yyVel+=t*accY;
        xxPos += t*xxVel;
        yyPos += t*yyVel;
    }


}

