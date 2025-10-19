package patron;

import java.util.*;
import paymentsService.Payment;

public class PatronManagerService {
    private final List<Patron> patrons;

    public PatronManagerService() {
        this.patrons = new ArrayList<Patron>();
    }

    public void removePatronById(String id) {
        if (id == null) {
            return;
        }
        for (int i = 0; i < patrons.size(); i++) {
            Patron patron = patrons.get(i);
            if (patron.getId().equals(id)) {
                patrons.remove(i);
                break;
            }
        }
    }

    public Patron searchPatronById(String id) {
        if (id == null) {
            return null;
        }
        for (Patron patron : patrons) {
            if (patron.getId().equals(id)) {
                return patron;
            }
        }
        return null;
    }

    public List<Patron> searchPatronByName(String name) {
        List<Patron> result = new ArrayList<Patron>();
        if (name == null) {
            return result;
        }
        for (Patron patron : patrons) {
            if (patron.getName().equalsIgnoreCase(name)) {
                result.add(patron);
            }
        }
        return result;
    }

    public List<Patron> listAllPatrons() {
        return new ArrayList<Patron>(patrons);
    }

    public boolean addPatron(Patron patron, Payment payment) {
        if (patron == null || payment == null) return false;
        if (payment.processPayment()) {
            patrons.add(patron);
            System.out.println( patron.getName()+"  added successfully with " + payment.getPaymentType());
            return true;

        }
        System.out.println(patron.getName()+"  payment failed. Patron not added.");
        return false;
    }
}
