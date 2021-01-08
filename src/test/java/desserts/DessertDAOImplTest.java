package desserts;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DessertDAOImplTest {

    DessertDAOImpl dessertDAO = new DessertDAOImpl();

    @Test
    void iHaveANonNullDAO() {
        assertNotNull(dessertDAO);
    }

    @Test
    void canIGetDesserts() {

        // when
        List<DessertDTO> desserts = dessertDAO.getDesserts();

        // then
        assertNotNull(desserts);
        assertTrue(desserts.size() > 0);

    }

    @Test
    void canIUpdateADessert() {

        // setup
        DessertDTO toUpdate = new DessertDTO("Was good", true);
        dessertDAO.create(toUpdate);

        // when
        toUpdate.setGood(false);
        dessertDAO.update(toUpdate);
        List<DessertDTO> desserts = dessertDAO.getDesserts();
        DessertDTO updatedDesert = null;
        for (DessertDTO newDesert : desserts) {
            if (newDesert.getName().equals("Was good")) {
                updatedDesert = newDesert;
            }
        }

        // then
        assertNotNull(updatedDesert);
        assertFalse(updatedDesert.isGood());

    }

}