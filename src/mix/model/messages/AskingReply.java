package mix.model.messages;

public class AskingReply<ASKING,REPLY> {
    private ASKING request;
    private REPLY reply;

    public AskingReply(ASKING request,  REPLY reply) {
        setRequest(request);
        setReply(reply);
    }

    public ASKING getRequest() {
        return request;
    }

    private void setRequest(ASKING request) {
        this.request = request;
    }

    public REPLY getReply() {
        return reply;
    }

    public void setReply(REPLY reply) {
        this.reply = reply;
    }

    @Override
    public String toString() {
        return "Request: "+ request.toString() + " Reply: " + ((reply!=null)?reply.toString():"waiting for reply...");
    }

}
