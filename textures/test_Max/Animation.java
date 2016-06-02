
import java.util.ArrayList;
public class Animation
{
    private ArrayList<String> frames = new ArrayList<String>();
    private int playHead;
    private String currentFrame;
    private AnimationType aType;
    
    public Animation (ArrayList<String> URLs, AnimationType at)
    {
	for (String frame : URLs)
	    {
		frames.add(frame);
	    }
	playHead = 0;
	currentFrame = frames.get(0);
	aType = at;
    }

    public AnimationType getType()
    {
	return aType;
    }

    public boolean next()
    {
	playHead += 1;
	if (playHead >= frames.size())
	    {
		return true;
	    }
	else
	    {
		return false;
	    }
    }

    public String getFrame()
    {
	if (playHead >= frames.size())
	    {
		return frames.get(frames.size() -1);
	    }
	else
	    {
		return frames.get(playHead);
	    }
    }

    public void reset()
    {
	playHead = 0;
    }
    
}
