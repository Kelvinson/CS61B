import java.lang.reflect.Parameter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;
public class NBody {
    public static double readRadius(String planetsfile) {
        In in = new In(planetsfile);
        int line = 0;
        /* Keep looking until the file is empty. */
        double Radius = 0;
        while (line < 2) {
			/* Each line has the rank of a country, then its
			 * name, then its production in metric tons, and
			 * finally the fraction of world salt output it produces. */

            Radius = in.readDouble();
            line++;
        }
        return Radius;
    }
    public static Planet[] readPlanets(String planetsfile){

        In in = new In(planetsfile);
        int Num = in.readInt();
        double Radius = in.readDouble();
        Planet[] result = new Planet[Num];
		/* Keep looking until the file is empty. */
        int index = 0;
        for(;index<Num;index++){
            result[index] = (Planet) getPlanet(in);
        }
        return result;
    }
    public static Planet getPlanet(In in){
        Planet p = new Planet(0,0,0,0,0,"");
        p.xxPos = in.readDouble();
        p.yyPos = in.readDouble();
        p.xxVel = in.readDouble();
        p.yyVel = in.readDouble();
        p.mass = in.readDouble();
        p.imgFileName = in.readString();

        return p;
    }

    public static void main(String [] args){
        double T = 157788000.0;
        double dt = 25000.0;
        int loop = 0;
        String pfile = "data/planets.txt";
        String  saveFileName = "newState.txt";
        if(args.length==3){
             T = Double.parseDouble(args[0]);
             dt = Double.parseDouble(args[1]);
            pfile = args[2];
        }
        double uniRadius = readRadius(pfile);
        Planet[] planets = readPlanets(pfile);
        int N = planets.length;
        StdDraw.setScale(-uniRadius, uniRadius);
        StdDraw.clear();
        StdAudio.play("audio/2001.mid");
        StdDraw.picture(0, 0, "images/starfield.jpg");
        for(Planet p : planets){
            p.draw();
        }
        StdDraw.show(5000);

        for(;loop<T;loop+=dt){
            double[] xForces= new double[N];
            double[] yForces = new double[N];
            int i = 0;
            for(Planet p : planets){
              xForces[i] =  p.calcNetForceExertedByX(planets);
               yForces[i] = p.calcNetForceExertedByX(planets);
               p.update(dt,xForces[i],yForces[i]);
                i++;
            }
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Planet p : planets){
                p.draw();
            }
            StdDraw.show(10);
        }

        StdOut.printf("%d\n",planets.length);
        StdOut.printf("%.2e\n",uniRadius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(saveFileName))){
//            String co
                    bw.write("%d\n,%.2e\n",planets.length, (int) uniRadius);
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
