package org.wesss.general_utils.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Tags that the given Object may be null
 */
@Retention(SOURCE)
@Target({FIELD, PARAMETER, LOCAL_VARIABLE})
public @interface Nullable {

}
