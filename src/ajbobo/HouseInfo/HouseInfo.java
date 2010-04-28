package ajbobo.HouseInfo;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class HouseInfo extends Activity
{
	private static Button btnDoIt;
	private static EditText txtRealtorFees;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		btnDoIt = (Button) findViewById(R.id.btnDoIt);
		btnDoIt.setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				calculatePossibleMortgage();
			}
		});

		txtRealtorFees = (EditText) findViewById(R.id.txtRealtorFees);
		txtRealtorFees.setOnEditorActionListener(new OnEditorActionListener()
		{
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
			{
				calculateRealtorFees();
				return true;
			}
		});
		
		calculateRealtorFees();
	}
	
	/** Get the number that is displayed in an EditText */
	private float getNumFromControl(int id)
	{
		EditText control = (EditText)findViewById(id);
		String value = control.getText().toString();
		float val = Float.parseFloat(value);
		
		return val;
	}

	/** Calculate and display the possible mortgage, and the amount that needs to be financed */
	private void calculatePossibleMortgage()
	{
		calculateRealtorFees();
		
		float sellingprice = getNumFromControl(R.id.txtSellingPrice);
		float currentmortgage = getNumFromControl(R.id.txtCurrentMortgage);
		float feepercent = getNumFromControl(R.id.txtRealtorFees) / 100;
		float realtorfees = sellingprice * feepercent;
		float closingcosts = getNumFromControl(R.id.txtClosingCosts);
		
		float profit = sellingprice - currentmortgage - realtorfees - closingcosts;
		
		float downpayment = getNumFromControl(R.id.txtDownPayment) / 100;
		
		float maxpossible = profit / downpayment;
		EditText possible = (EditText)findViewById(R.id.txtPossibleMortgage);
		possible.setText(Double.toString(maxpossible));
		
		EditText finance = (EditText)findViewById(R.id.txtFinanceAmount);
		finance.setText(Double.toString(maxpossible * (1 - downpayment)));
		
	}

	/** Calculate and display the realtor fees */
	private void calculateRealtorFees()
	{
		 float feepercent = getNumFromControl(R.id.txtRealtorFees) / 100;
		 float sellingprice = getNumFromControl(R.id.txtSellingPrice);
		 
		 double res = sellingprice * feepercent;
		 
		 EditText showfees = (EditText)findViewById(R.id.txtShowRealtorFees);
		 showfees.setText(Double.toString(res));
	}
}