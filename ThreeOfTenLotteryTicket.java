import java.util.ArrayList;

public class ThreeOfTenLotteryTicket {
    private static int ticketsSold = 0; // static because this is class-level.  We want only one of these!
    private int ticketNumber;
    private ArrayList<Integer> picks;

    /**
     * Constructs a "quick pick" ticket, one for which numbers are picked randomly for the user
     * After the static variable ticketsSold has been incremented, ticketNumber is assigned the value of ticketsSold
     * the three integers assigned to picks are determined by calls to pickUniqueVal(), and the ticket is then validated
     */
    public ThreeOfTenLotteryTicket() { // a Quick Pick ticket
        ticketsSold++;
        ticketNumber = ticketsSold;
        picks = new ArrayList<Integer>();
        for (int i = 0; i < 3; i++) {
            picks.add(pickUniqueVal());
        }

        if (!validate()) {
            throw new IllegalStateException("ticket invalid");
        }
    }

    /**
     * Constructs a ticket with numbers matching the users choices
     *
     * @param choices a reference to the array of int containing the user's three number choices
     *                ticketsSold and ticketNumber behave as in the default quick-pick constructor
     *                the ticket is validated after picks has been filled.
     */
    public ThreeOfTenLotteryTicket(int[] choices) { // user's choice lotter ticket
        ticketsSold++;
        ticketNumber = ticketsSold;
        picks = new ArrayList<Integer>();
        for (int i = 0; i < choices.length; i++) {
            {
                picks.add(choices[i]);
            }
        }
        if (!validate()) {
            throw new IllegalStateException("ticket invalid");
        }

    }

    /**
     * @return an Integer with value in rage 1-10 inclusive such that
     * this Integers is NOT contained in the list picks.
     */
    private Integer pickUniqueVal() {
        int r = (int) ((Math.random() * 10)) + 1;
        while (inPicks(r)) {
            r = (int) ((Math.random() * 10)) + 1;
        }
        return r;
    }

    private boolean inPicks(int num) {
        if (picks.size() == 0) {
            return false;
        }
        for (int n : picks) {
            if (n == num) {
                return true;
            }
        }
        return false;
    }

    /*
        returns true only if picks contains 3 unique numbers in range 1-10
    */
    public boolean validate() {
        if (picks.size() != 3)
            return false;
        if (picks.get(0) == picks.get(1) || picks.get(1) == picks.get(2) || picks.get(0) == picks.get(2)) {
            return false;
        }
        for (int num : picks) {
            if (num < 1 || num > 10)
                return false;
        }
        return true;
    }

    // returns true iff this ticket matches winningTicket, containing the same numbers (order not important)
    public boolean grandPrizeWinner(ThreeOfTenLotteryTicket winningTicket) {
        boolean n1 = false;
        boolean n2 = false;
        boolean n3 = false;
        for (int val : winningTicket.picks) {
            if (val == picks.get(0))
                n1 = true;
            if (val == picks.get(1))
                n2 = true;
            if (val == picks.get(2))
                n3 = true;
        }
        return n1 && n2 && n3;
    }

    public String toString() {
        return "3 of 10 lottery ticket #" + ticketNumber + ", picks: " + picks.toString();
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public static void main(String[] args) {
        System.out.println("Let's test the 3 of 10 Lottery game!\n");
        int[] myNums = {1, 5, 9};

        ThreeOfTenLotteryTicket myTicket = new ThreeOfTenLotteryTicket(myNums);
        //LittleLotteryTicket winningTicket = new LittleLotteryTicket(myNums);  // used to test grandPrizeWinner
        int gamesPlayed = 0;
        boolean notRich = true;
        while (notRich) {
            ThreeOfTenLotteryTicket winningTicket = new ThreeOfTenLotteryTicket();
            gamesPlayed++;
            System.out.println("my ticket is " + myTicket);
            System.out.println("The winning ticket is " + winningTicket);
            if (myTicket.grandPrizeWinner(winningTicket)) {
                System.out.println("\nI won after " + gamesPlayed + " games!!! I'm rich!!! WoooooooooHoooooooooo!!!!");
                notRich = false;
            } else {
                System.out.println("Shucks......\n");
            }
        }
    }

}

