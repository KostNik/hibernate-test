@GenericGenerators({

        @GenericGenerator(
                name = Constants.ID_GENERATOR,
                strategy = "enhanced-sequence",
//                strategy = "org.com.edu.hibernate.id.enhanced.SequenceStyleGenerator",
                parameters = {
                        @Parameter(
                                name = SEQUENCE_PARAM,
                                value = Constants.ID_GENERATOR_SEQUENCE_NAME
                        ),
                        @Parameter(
                                name = OPT_PARAM,
                                value = "pooled"
                        ),
                        @Parameter(
                                name = INCREMENT_PARAM,
                                value = "1"
                        ),
                        @Parameter(
                                name = INITIAL_PARAM,
                                value = "1000"
                        )
                })
})


package com.edu.hibernate.model;

import com.edu.hibernate.Constants;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import org.hibernate.annotations.Parameter;

import static org.hibernate.id.enhanced.SequenceStyleGenerator.*;
