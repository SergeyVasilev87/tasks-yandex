import java.util.*;

public class Duma {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String line = null;
        Map<String, Integer> map = new LinkedHashMap<>();
        int allVotes = 0;
        try {
            while (true) {
                line = scanner.nextLine();
                if (line.isEmpty())
                    break;
                String[] s = line.split(" ");

                StringBuilder nameConsignment = new StringBuilder();
                int numberOfVotes = 0;
                for (int i = 0; i < s.length; i++) {
                    if (i < s.length - 1)
                        nameConsignment.append(s[i] + " ");
                    else
                        numberOfVotes = Integer.parseInt(s[i]);
                    allVotes += numberOfVotes;
                }
                map.put(nameConsignment.toString(), numberOfVotes);
            }

        } catch (Exception e) {

        }

        double votingNumber = (double) allVotes/450;

        Map<String, Integer> numberOfSeats = new LinkedHashMap<>();
        Map<String, Double> remainderOfDivision = new HashMap<>();

        int numberOfClearance = 450;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            int temp = (int) (entry.getValue() / votingNumber);
            double v = entry.getValue() / votingNumber;
            numberOfClearance -= temp;
            numberOfSeats.put(entry.getKey(), temp);
            remainderOfDivision.put(entry.getKey(), v - temp);
        }

        List<Map.Entry<String, Double>> list = new LinkedList<>(remainderOfDivision.entrySet());
        list.sort(new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                if (o1.getValue().equals(o2.getValue())) {
                    return map.get(o2.getKey()) - map.get(o1.getKey());

                } else
                    return o2.getValue().compareTo(o1.getValue());
            }
        });
        while (numberOfClearance > 0) {
            Map.Entry<String, Double> remove = list.remove(0);
            numberOfSeats.merge(remove.getKey(), 1, (integer, integer2) -> integer += integer2);
            numberOfClearance--;
        }

        for (Map.Entry<String, Integer> entry: numberOfSeats.entrySet()) {
            System.out.println(entry.getKey() + entry.getValue());
        }
    }
}
