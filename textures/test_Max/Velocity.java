
import java.lang.Math;
public class Velocity
{
    private double speed;
    private double angle;
    private double speedX;
    private double speedY;

    public Velocity(double s, double a)
    {
	speed = s;
	angle = a;
	updateSpeeds();
    }

    private void updateSpeeds()
    {
	speedX = speed * Math.cos(angle);
	speedY = speed * Math.sin(angle);
    }

    public void updateAngle(double a)
    {
	angle = a;
	updateSpeeds();
    }

    public void updateSpeed(double s)
    {
	speed = s;
	updateSpeeds();
    }

    public double getXVelocity()
    {
	return speedX;
    }

    public double getYVelocity()
    {
	return speedY;
    }

    public double getAngle()
    {
	return angle;
    }
	
	public void reflectX() {
		speedX *= -1;
		angle = Math.atan2( speedY/speedX );
	}
	
	public void reflectY() {
		speedY *= -1;
		angle = Math.atan2( speedY/speedX );
	}
	
}
