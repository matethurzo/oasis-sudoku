/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 * <p>
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * <p>
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.oasis.sudoku.resource;

import com.oasis.sudoku.SudokuSolver;
import com.oasis.sudoku.dto.SudokuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Mate Thurzo
 */
@Path("/sudoku")
@Component
public class SudokuController {

    @Autowired
    public SudokuController(SudokuSolver solver) {
        this.solver = solver;
    }

    @Path("/solve")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @POST
    public Response solve(SudokuDTO dto) {
        SudokuDTO responseDTO = new SudokuDTO();

        responseDTO.setBoard(solver.solve(dto.getBoard()));

        return Response.ok()
                .entity(responseDTO)
                .build();
    }

    private SudokuSolver solver;

}
