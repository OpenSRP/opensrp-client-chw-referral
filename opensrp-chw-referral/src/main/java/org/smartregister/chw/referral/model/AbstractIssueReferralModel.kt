package org.smartregister.chw.referral.model

import androidx.lifecycle.ViewModel
import org.smartregister.chw.referral.contract.BaseIssueReferralContract
import org.smartregister.chw.referral.domain.MemberObject

abstract class AbstractIssueReferralModel : ViewModel(),
    BaseIssueReferralContract.Model {

    var memberObject: MemberObject? = null
}