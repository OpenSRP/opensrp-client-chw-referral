package org.smartregister.chw.referral.util;

import org.joda.time.DateTime;
import org.smartregister.chw.referral.ReferralLibrary;
import org.smartregister.chw.referral.domain.ReferralTask;
import org.smartregister.domain.Task;
import org.smartregister.repository.AllSharedPreferences;
import org.smartregister.repository.BaseRepository;

import java.util.UUID;

public class ReferralUtil {

    public static void createReferralTask(ReferralTask referralTask, AllSharedPreferences allSharedPreferences) {
        Task task = new Task();
        task.setIdentifier(UUID.randomUUID().toString());
        //TODO Implement plans remove hard coded plan (in 2020 road-map)
      /*  Iterator<String> iterator = ChwApplication.getInstance().getPlanDefinitionRepository()
                .findAllPlanDefinitionIds().iterator();
        if (iterator.hasNext()) {
            task.setPlanIdentifier(iterator.next());
        } else {

            Timber.e("No plans exist in the server");
        }*/
        task.setPlanIdentifier(Constants.REFERRAl.PLAN_ID);
        task.setGroupIdentifier(referralTask.getGroupId());
        task.setStatus(Task.TaskStatus.READY);
        task.setBusinessStatus(Constants.BUSINESS_STATUS.REFERRED);
        task.setPriority(3);
        task.setCode(Constants.REFERRAl.CODE);
        task.setDescription(referralTask.getReferralDescription());
        task.setFocus(referralTask.getFocus());
        task.setForEntity(referralTask.getEvent().getBaseEntityId());
        DateTime now = new DateTime();
        task.setExecutionStartDate(now);
        task.setReasonReference(referralTask.getEvent().getEventId());
        task.setAuthoredOn(now);
        task.setLastModified(now);
        task.setOwner(allSharedPreferences.fetchRegisteredANM());
        task.setSyncStatus(BaseRepository.TYPE_Created);
        task.setRequester(allSharedPreferences.getANMPreferredName(allSharedPreferences.fetchRegisteredANM()));
        task.setLocation(allSharedPreferences.fetchUserLocalityId(allSharedPreferences.fetchRegisteredANM()));
        ReferralLibrary.getInstance().getTaskRepository().addOrUpdate(task);
    }
}
