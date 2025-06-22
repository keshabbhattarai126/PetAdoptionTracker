/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package petadoptiontracker.dao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import petadoptiontracker.model.Review;

public class ReviewDaoTest {

    private ReviewDao reviewDao;

    @Before
    public void setUp() {
        reviewDao = new ReviewDao();
    }

    @Test
    public void testAddReview() {
        Review review = new Review("JUnit review text", 5);
        boolean result = reviewDao.addReview(review);
        Assert.assertTrue(result);
    }
}
