import java.util.Random;
import java.util.List;

interface Notifiable {
    void notify(String s);
}

class User implements Notifiable {
    private final String id;

    User(String id) {
        this.id = id;
    }
    
    Bot join(Bot bot) {
        return bot.connect(this);
    }

    public void notify(String s) {
        System.out.println(this.id + ": " + s);
    }

    public String toString() {
        return this.id;
    }
}

class Bot {
    private final String id;
    private final ImList<Notifiable> users;

    Bot() {
        this(List.<Notifiable>of());
    }

    private Bot(String id, ImList<Notifiable> users) {
        this.id = id;
        this.users = users;
    }

    Bot(List<Notifiable> users) {
        this(new Random().nextInt(1000) + "", new ImList<Notifiable>(users));
    }

    Bot connect(Notifiable user) {
        String s = this.toString() + " says hi to " + user.toString();

        Bot newBot = new Bot(this.id, this.users.add(user));
        for (Notifiable u : newBot.users) {
            u.notify(s);
        }
        return newBot;
    }

    public String toString() {
        return "bot@" + this.id;
    }
}
