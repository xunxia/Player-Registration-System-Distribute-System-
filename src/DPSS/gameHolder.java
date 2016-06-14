package DPSS;

/**
 * Holder class for : game
 * 
 * @author OpenORB Compiler
 */
final public class gameHolder
        implements org.omg.CORBA.portable.Streamable
{
    /**
     * Internal game value
     */
    public DPSS.game value;

    /**
     * Default constructor
     */
    public gameHolder()
    { }

    /**
     * Constructor with value initialisation
     * @param initial the initial value
     */
    public gameHolder(DPSS.game initial)
    {
        value = initial;
    }

    /**
     * Read game from a marshalled stream
     * @param istream the input stream
     */
    public void _read(org.omg.CORBA.portable.InputStream istream)
    {
        value = gameHelper.read(istream);
    }

    /**
     * Write game into a marshalled stream
     * @param ostream the output stream
     */
    public void _write(org.omg.CORBA.portable.OutputStream ostream)
    {
        gameHelper.write(ostream,value);
    }

    /**
     * Return the game TypeCode
     * @return a TypeCode
     */
    public org.omg.CORBA.TypeCode _type()
    {
        return gameHelper.type();
    }

}
