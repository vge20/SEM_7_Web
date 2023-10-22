package com.baeldung.StateMachine.Factory;

import com.baeldung.StateMachine.States.AddDoctor.AddDoctorFirstNameScanState;
import com.baeldung.StateMachine.States.AddDoctor.AddDoctorGenderScanState;
import com.baeldung.StateMachine.States.AddDoctor.AddDoctorLastNameScanState;
import com.baeldung.StateMachine.States.AddDoctor.AddDoctorSpecializationScanState;
import com.baeldung.StateMachine.States.AddRecord.AddRecordDateScanState;
import com.baeldung.StateMachine.States.AddRecord.AddRecordDoctorIdScanState;
import com.baeldung.StateMachine.States.AddRecord.AddRecordTimeScanState;
import com.baeldung.StateMachine.States.AddRecord.AddRecordUserLoginScanState;
import com.baeldung.StateMachine.States.AddUser.*;
import com.baeldung.StateMachine.States.DeleteDoctor.DoctorForDeleteIdScanState;
import com.baeldung.StateMachine.States.DeleteRecord.*;
import com.baeldung.StateMachine.States.DeleteUser.UserForDeleteLoginScanState;
import com.baeldung.StateMachine.States.LogIn.UnregUserLoginScanState;
import com.baeldung.StateMachine.States.LogIn.UnregUserPasswordScanState;
import com.baeldung.StateMachine.States.Menus.AdminMenuState;
import com.baeldung.StateMachine.States.Menus.StartMenuState;
import com.baeldung.StateMachine.States.Menus.UserMenuState;
import com.baeldung.StateMachine.States.ParentState.State;
import com.baeldung.StateMachine.States.Registration.*;
import com.baeldung.StateMachine.States.UpdateAccount.*;
import com.baeldung.StateMachine.States.UpdateDoctor.*;
import com.baeldung.StateMachine.States.UpdateRecord.*;
import com.baeldung.StateMachine.States.UpdateUser.*;
import org.slf4j.Logger;

public class StateFactory {

    public State getStateClass(int state, Logger logger) {
        switch (state) {
            case 0:
                return new StartMenuState(logger);
            case 1:
                return new UnregUserLoginScanState(logger);
            case 2:
                return new UnregUserPasswordScanState(logger);
            case 3:
                return new UserMenuState(logger);
            case 4:
                return new RegistrationFirstNameScanState(logger);
            case 5:
                return new RegistrationLastNameScanState(logger);
            case 6:
                return new RegistrationGenderScanState(logger);
            case 7:
                return new RegistrationBirthDateScanState(logger);
            case 8:
                return new RegistrationLoginScanState(logger);
            case 9:
                return new RegistrationPasswordScanState(logger);
            case 10:
                return new AdminMenuState(logger);
            case 11:
                return new AddUserFirstNameScanState(logger);
            case 12:
                return new AddUserLastNameScanState(logger);
            case 13:
                return new AddUserGenderScanState(logger);
            case 14:
                return new AddUserBirthDateScanState(logger);
            case 15:
                return new AddUserLoginScanState(logger);
            case 16:
                return new AddUserPasswordScanState(logger);
            case 17:
                return new UserForDeleteLoginScanState(logger);
            case 18:
                return new UserForUpdateLoginScanState(logger);
            case 19:
                return new UpdateUserFirstNameScanState(logger);
            case 20:
                return new UpdateUserLastNameScanState(logger);
            case 21:
                return new UpdateUserGenderScanState(logger);
            case 22:
                return new UpdateUserBirthDateScanState(logger);
            case 23:
                return new UpdateUserLoginScanState(logger);
            case 24:
                return new UpdateUserPasswordScanState(logger);
            case 25:
                return new AddDoctorFirstNameScanState(logger);
            case 26:
                return new AddDoctorLastNameScanState(logger);
            case 27:
                return new AddDoctorGenderScanState(logger);
            case 28:
                return new AddDoctorSpecializationScanState(logger);
            case 29:
                return new DoctorForDeleteIdScanState(logger);
            case 30:
                return new DoctorForUpdateIdScanState(logger);
            case 31:
                return new UpdateDoctorFirstNameScanState(logger);
            case 32:
                return new UpdateDoctorLastNameScanState(logger);
            case 33:
                return new UpdateDoctorGenderScanState(logger);
            case 34:
                return new UpdateDoctorSpecializationScanState(logger);
            case 35:
                return new UpdateAccountFirstNameScanState(logger);
            case 36:
                return new UpdateAccountLastNameScanState(logger);
            case 37:
                return new UpdateAccountGenderScanState(logger);
            case 38:
                return new UpdateAccountBirthDateScanState(logger);
            case 39:
                return new UpdateAccountLoginScanState(logger);
            case 40:
                return new UpdateAccountPasswordScanState(logger);
            case 41:
                return new AddRecordUserLoginScanState(logger);
            case 42:
                return new AddRecordDoctorIdScanState(logger);
            case 43:
                return new AddRecordDateScanState(logger);
            case 44:
                return new AddRecordTimeScanState(logger);
            case 46:
                return new DeleteRecordUserLoginScanState(logger);
            case 47:
                return new DeleteRecordDoctorIdScanState(logger);
            case 48:
                return new DeleteRecordDateScanState(logger);
            case 49:
                return new DeleteRecordTimeScanState(logger);
            case 51:
                return new UpdateRecordUserLoginScanState(logger);
            case 52:
                return new UpdateRecordDoctorIdScanState(logger);
            case 53:
                return new UpdateRecordDateScanState(logger);
            case 54:
                return new UpdateRecordTimeScanState(logger);
            case 55:
                return new UpdateRecordNewDateScanState(logger);
            case 56:
                return new UpdateRecordNewTimeScanState(logger);
            default:
                return null;
        }
    }
}
