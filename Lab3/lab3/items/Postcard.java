package lab3.items;

public class Postcard extends Item {
    private Content content;
    public Postcard(String content, String sign) {
        super("Postcard");
        this.content = new Content(content, sign);
    }
    public Content getContent() {
        return content;
    }
    public class Content{
        private String text;
        private String sign;
        public Content(String text, String sign){
            this.text= text;
            this.sign = sign;
        }
        public String getText() {
            return text;
        }

        public String getSign() {
            return sign;
        }
    }
    public String getText() {
        return content.getText();
    }

    public String getSign() {
        return content.getSign();
    }

}
