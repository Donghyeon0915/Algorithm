#include <stdio.h>
#include <algorithm>
using namespace std;

int coffee[11] = { 0, 1400, 1500, 1000, 1000, 1700, 1900, 1500, 1900, 500, 2300 };

int main() {
	int juice, coupon, price_sum, i, cnt, plus_coupon = 0;
	int bonus[100];
	char order, buffer;

	scanf("%d %d", &juice, &coupon);
	scanf("%c", &buffer);

	for (price_sum= 0, i = 0, cnt = 0; i < juice; i++) {
		scanf(" %c", &order);
		price_sum += coffee[order - 64];

		if (order == 'A' || order == 'B' || order == 'E' || order == 'F' || order == 'H' || order == 'J') plus_coupon++, bonus[cnt++] = coffee[order - 64];
	}

	sort(bonus, bonus + cnt);

	if ((coupon + plus_coupon) > 10) printf("%d\n", price_sum - bonus[0]);
	else printf("%d\n", price_sum);

	return 0;
}