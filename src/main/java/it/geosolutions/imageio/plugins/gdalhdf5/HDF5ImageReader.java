/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.geosolutions.imageio.plugins.gdalhdf5;

import it.geosolutions.imageio.gdalframework.GDALImageReader;

/**
 *
 * @author uloup
 */
public class HDF5ImageReader extends GDALImageReader {
     /**
     * Constructs a
     * <code>HDF5ImageReader</code> using a {@link HDF5ImageReaderSpi}.
     * 
     * @param originatingProvider
     *            The {@link HDF5ImageReaderSpi} to use for building this
     *            <code>HDF5ImageReader</code>.
     */
    public HDF5ImageReader(HDF5ImageReaderSpi originatingProvider) {
        super(originatingProvider);
    }
}
