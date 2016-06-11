
public enum ObjectOverlapType
{
    LEFT(true), RIGHT(true), UP(true), DOWN(true), NONE(false);

    private final boolean isTouching;
    private ObjectOverlapType(boolean b)
    {
	isTouching = b;
    }
    public boolean getIsTouching()
    {
	return isTouching;
    }
}
