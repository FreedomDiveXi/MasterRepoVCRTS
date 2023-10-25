package test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Vehicle;

public class VehicleTest {

		@Before
		public void setUp() throws Exception {
		}

		@After
		public void tearDown() throws Exception {
		}

	    @Test
	    public void testCreateVehicleWithUniqueID() {
	        Vehicle vehicle = new Vehicle(1, "Toyota", "Camry", 2018);
	        assertEquals(1, vehicle.getVehicleId());
	    }

	    @Test
	    public void testCreateVehicleWithDuplicateID() {
	        Vehicle vehicle1 = new Vehicle(2, "Toyota", "Camry", 2019);
	        try {
	            Vehicle vehicle2 = new Vehicle(2, "Toyota", "Avalon", 2019);
	            fail("Expected IllegalArgumentException for duplicate vehicleId");
	        } catch (IllegalArgumentException e) {
	            assertEquals("Duplicate vehicleId, please input a number other than 2", e.getMessage());
	        }
	    }

	    @Test
	    public void testGetMake() {
	        Vehicle vehicle = new Vehicle(3, "Toyota", "Camry", 2020);
	        assertEquals("Toyota", vehicle.getMake());
	    }

	    @Test
	    public void testGetModel() {
	        Vehicle vehicle = new Vehicle(4, "Toyota", "Camry", 2021);
	        assertEquals("Camry", vehicle.getModel());
	    }

	    @Test
	    public void testGetYear() {
	        Vehicle vehicle = new Vehicle(5, "Toyota", "Camry", 2022);
	        assertEquals(2022, vehicle.getYear());
	    }
	}