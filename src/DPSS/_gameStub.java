package DPSS;

/**
 * Interface definition: game.
 * 
 * @author OpenORB Compiler
 */
public class _gameStub extends org.omg.CORBA.portable.ObjectImpl
        implements game
{
    static final String[] _ids_list =
    {
        "IDL:DPSS/game:1.0"
    };

    public String[] _ids()
    {
     return _ids_list;
    }

    private final static Class _opsClass = DPSS.gameOperations.class;

    /**
     * Operation createPlayerAccount
     */
    public boolean createPlayerAccount(String firstName, String lastName, int age, String userName, String password, String IPAddress)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("createPlayerAccount",true);
                    _output.write_string(firstName);
                    _output.write_string(lastName);
                    _output.write_long(age);
                    _output.write_string(userName);
                    _output.write_string(password);
                    _output.write_string(IPAddress);
                    _input = this._invoke(_output);
                    boolean _arg_ret = _input.read_boolean();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("createPlayerAccount",_opsClass);
                if (_so == null)
                   continue;
                DPSS.gameOperations _self = (DPSS.gameOperations) _so.servant;
                try
                {
                    return _self.createPlayerAccount( firstName,  lastName,  age,  userName,  password,  IPAddress);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation playerSignIn
     */
    public String playerSignIn(String userName, String password, String IPAddress)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("playerSignIn",true);
                    _output.write_string(userName);
                    _output.write_string(password);
                    _output.write_string(IPAddress);
                    _input = this._invoke(_output);
                    String _arg_ret = _input.read_string();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("playerSignIn",_opsClass);
                if (_so == null)
                   continue;
                DPSS.gameOperations _self = (DPSS.gameOperations) _so.servant;
                try
                {
                    return _self.playerSignIn( userName,  password,  IPAddress);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation playerSignOut
     */
    public String playerSignOut(String userName, String IPAddress)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("playerSignOut",true);
                    _output.write_string(userName);
                    _output.write_string(IPAddress);
                    _input = this._invoke(_output);
                    String _arg_ret = _input.read_string();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("playerSignOut",_opsClass);
                if (_so == null)
                   continue;
                DPSS.gameOperations _self = (DPSS.gameOperations) _so.servant;
                try
                {
                    return _self.playerSignOut( userName,  IPAddress);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation getPlayerStatus
     */
    public String getPlayerStatus(String AdminUserName, String adminPassword, String IPAddress)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("getPlayerStatus",true);
                    _output.write_string(AdminUserName);
                    _output.write_string(adminPassword);
                    _output.write_string(IPAddress);
                    _input = this._invoke(_output);
                    String _arg_ret = _input.read_string();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("getPlayerStatus",_opsClass);
                if (_so == null)
                   continue;
                DPSS.gameOperations _self = (DPSS.gameOperations) _so.servant;
                try
                {
                    return _self.getPlayerStatus( AdminUserName,  adminPassword,  IPAddress);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation transferAccount
     */
    public boolean transferAccount(String userName, String passWord, String OldIPAddress, String NewIPAddress)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("transferAccount",true);
                    _output.write_string(userName);
                    _output.write_string(passWord);
                    _output.write_string(OldIPAddress);
                    _output.write_string(NewIPAddress);
                    _input = this._invoke(_output);
                    boolean _arg_ret = _input.read_boolean();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("transferAccount",_opsClass);
                if (_so == null)
                   continue;
                DPSS.gameOperations _self = (DPSS.gameOperations) _so.servant;
                try
                {
                    return _self.transferAccount( userName,  passWord,  OldIPAddress,  NewIPAddress);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

    /**
     * Operation suspendAccount
     */
    public boolean suspendAccount(String AdminUserName, String AdminPassword, String adminIPAddress, String userNameToSuspend)
    {
        while(true)
        {
            if (!this._is_local())
            {
                org.omg.CORBA.portable.InputStream _input = null;
                try
                {
                    org.omg.CORBA.portable.OutputStream _output = this._request("suspendAccount",true);
                    _output.write_string(AdminUserName);
                    _output.write_string(AdminPassword);
                    _output.write_string(adminIPAddress);
                    _output.write_string(userNameToSuspend);
                    _input = this._invoke(_output);
                    boolean _arg_ret = _input.read_boolean();
                    return _arg_ret;
                }
                catch(org.omg.CORBA.portable.RemarshalException _exception)
                {
                    continue;
                }
                catch(org.omg.CORBA.portable.ApplicationException _exception)
                {
                    String _exception_id = _exception.getId();
                    throw new org.omg.CORBA.UNKNOWN("Unexpected User Exception: "+ _exception_id);
                }
                finally
                {
                    this._releaseReply(_input);
                }
            }
            else
            {
                org.omg.CORBA.portable.ServantObject _so = _servant_preinvoke("suspendAccount",_opsClass);
                if (_so == null)
                   continue;
                DPSS.gameOperations _self = (DPSS.gameOperations) _so.servant;
                try
                {
                    return _self.suspendAccount( AdminUserName,  AdminPassword,  adminIPAddress,  userNameToSuspend);
                }
                finally
                {
                    _servant_postinvoke(_so);
                }
            }
        }
    }

}
