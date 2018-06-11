package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import zipcode.RestrictedZip;

public class RestrictedZipTest {

    @Test
    public void test() {

        final RestrictedZip restrictedZip = new RestrictedZip();
        final int[][] zips = { { 13, 14 }, { 1, 1 }, { 3, 10 }, { 4, 11 } };
        assertEquals(3, restrictedZip.getMergedRestrictedZipRanges(zips).size());
        assertEquals("[[13,14], [3,11], [1,1]]", restrictedZip.getMergedRestrictedZipRanges(zips).toString());

        final int[][] zip2 = { { 94133, 94133 }, { 94200, 94299 }, { 94600, 94699 } };
        assertEquals(3, restrictedZip.getMergedRestrictedZipRanges(zip2).size());
        assertEquals("[[94600,94699], [94200,94299], [94133,94133]]",
                restrictedZip.getMergedRestrictedZipRanges(zip2).toString());

        final int[][] zip3 = { { 94133, 94133 }, { 94200, 94299 }, { 94226, 94399 } };
        assertEquals(2, restrictedZip.getMergedRestrictedZipRanges(zip3).size());
        assertEquals("[[94200,94399], [94133,94133]]", restrictedZip.getMergedRestrictedZipRanges(zip3).toString());

        final int[][] zip4 = {};
        assertEquals(0, restrictedZip.getMergedRestrictedZipRanges(zip4).size());
        assertEquals("[]", restrictedZip.getMergedRestrictedZipRanges(zip4).toString());

        final int[][] zip5 = { { 2, 10 }, { 1, 1 }, { 1, 1 }, { 11, 11 } };
        assertEquals(1, restrictedZip.getMergedRestrictedZipRanges(zip5).size());
        assertEquals("[[1,11]]", restrictedZip.getMergedRestrictedZipRanges(zip5).toString());

        final int[][] zip6 = { { 3, 10 }, { 1, 1 }, { 1, 1 }, { 11, 11 } };
        assertEquals(2, restrictedZip.getMergedRestrictedZipRanges(zip6).size());
        assertEquals("[[3,11], [1,1]]", restrictedZip.getMergedRestrictedZipRanges(zip6).toString());

    }

}
