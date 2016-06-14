package DPSS;

/** 
 * Helper class for : game
 *  
 * @author OpenORB Compiler
 */ 
public class gameHelper
{
    /**
     * Insert game into an any
     * @param a an any
     * @param t game value
     */
    public static void insert(org.omg.CORBA.Any a, DPSS.game t)
    {
        a.insert_Object(t , type());
    }

    /**
     * Extract game from an any
     *
     * @param a an any
     * @return the extracted game value
     */
    public static DPSS.game extract( org.omg.CORBA.Any a )
    {
        if ( !a.type().equivalent( type() ) )
        {
            throw new org.omg.CORBA.MARSHAL();
        }
        try
        {
            return DPSS.gameHelper.narrow( a.extract_Object() );
        }
        catch ( final org.omg.CORBA.BAD_PARAM e )
        {
            throw new org.omg.CORBA.MARSHAL(e.getMessage());
        }
    }

    //
    // Internal TypeCode value
    //
    private static org.omg.CORBA.TypeCode _tc = null;

    /**
     * Return the game TypeCode
     * @return a TypeCode
     */
    public static org.omg.CORBA.TypeCode type()
    {
        if (_tc == null) {
            org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init();
            _tc = orb.create_interface_tc( id(), "game" );
        }
        return _tc;
    }

    /**
     * Return the game IDL ID
     * @return an ID
     */
    public static String id()
    {
        return _id;
    }

    private final static String _id = "IDL:DPSS/game:1.0";

    /**
     * Read game from a marshalled stream
     * @param istream the input stream
     * @return the readed game value
     */
    public static DPSS.game read(org.omg.CORBA.portable.InputStream istream)
    {
        return(DPSS.game)istream.read_Object(DPSS._gameStub.class);
    }

    /**
     * Write game into a marshalled stream
     * @param ostream the output stream
     * @param value game value
     */
    public static void write(org.omg.CORBA.portable.OutputStream ostream, DPSS.game value)
    {
        ostream.write_Object((org.omg.CORBA.portable.ObjectImpl)value);
    }

    /**
     * Narrow CORBA::Object to game
     * @param obj the CORBA Object
     * @return game Object
     */
    public static game narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof game)
            return (game)obj;

        if (obj._is_a(id()))
        {
            _gameStub stub = new _gameStub();
            stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
            return stub;
        }

        throw new org.omg.CORBA.BAD_PARAM();
    }

    /**
     * Unchecked Narrow CORBA::Object to game
     * @param obj the CORBA Object
     * @return game Object
     */
    public static game unchecked_narrow(org.omg.CORBA.Object obj)
    {
        if (obj == null)
            return null;
        if (obj instanceof game)
            return (game)obj;

        _gameStub stub = new _gameStub();
        stub._set_delegate(((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate());
        return stub;

    }

}
