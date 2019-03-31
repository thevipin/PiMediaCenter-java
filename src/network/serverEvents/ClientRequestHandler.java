package network.serverEvents;

public abstract class ClientRequestHandler {
	String Identifier="";
	public final String getIdentifier()
	{
		return Identifier;
	}
	public final void putIdentifier(String identifier)
	{
		Identifier=identifier;
	}
	public abstract void onClientRequest(DataCodex code);
}
