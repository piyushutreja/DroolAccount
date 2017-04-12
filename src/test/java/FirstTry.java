

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.api.runtime.rule.FactHandle;

import com.sample.entities.Account;
import com.sample.entities.util.KnowledgeSessionHelper;
import com.sample.entities.util.OutputDisplay;

public class FirstTry {

	StatelessKieSession sessionStateless;
	KieSession sessionStatefull;
	static KieContainer kieContainer;
	 RuleRuntimeEventListener ruleRuntimeEventListener;
	
	
	@BeforeClass
	public static void beforeClass()
	{
		kieContainer=KnowledgeSessionHelper.createRuleBase();
		
	}
	
	@Before
	public void before()
	{
		ruleRuntimeEventListener = new RuleRuntimeEventListener() {
			
			@Override
			public void objectUpdated(ObjectUpdatedEvent event) {
				System.out.println("Object Updated "+event.getObject().toString());
				
			}
			
			@Override
			public void objectInserted(ObjectInsertedEvent event) {
				System.out.println("Object inserted "+event.getObject().toString());
				
			}
			
			@Override
			public void objectDeleted(ObjectDeletedEvent event) {
				System.out.println("Object Deleted "+event.getOldObject().toString());
				
			}
		};
	}
	
	@Test
	public  void testFirstOne() {
		
		sessionStatefull=KnowledgeSessionHelper.getStatefulKnowledgeSession(kieContainer, "ksession-rules");
		OutputDisplay outputDisplay = new OutputDisplay();
		sessionStatefull.setGlobal("showResults", outputDisplay);
		
		sessionStatefull.fireAllRules();
	}
	
	@Test
	public  void testRuleOneWithFactAndUsageOfCallback() {
		
		sessionStateless=KnowledgeSessionHelper.getStatelessKnowledgeSession(kieContainer, "ksession-rules");		
		OutputDisplay outputDisplay = new OutputDisplay();
		sessionStateless.setGlobal("showResults", outputDisplay);	
		sessionStateless.addEventListener(ruleRuntimeEventListener);
		Account a = new Account();
		a.setAccountno(10);
		sessionStateless.
		FactHandle handlea = sessionStateless.insert(a);
		a.setBalance(12.0);
		sessionStateless.update(handlea, a);
		sessionStateless.delete(handlea);
		sessionStateless.fireAllRules();

				
		System.out.println("finally phew");
	}

}
