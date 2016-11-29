package abanoubm.engeel.main;

import java.util.ArrayList;

import abanoubm.engeel.data.Verse;

public class BibileInfo {

	public static String getArabicNum(int num) {
		char[] arr = { '٠', '١', '٢', '٣', '٤', '٥', '٦', '٧', '٨', '٩' };
		String arabic = "";

		while (num > 0) {
			arabic = arr[num % 10] + arabic;
			num /= 10;
		}

		return arabic;
	}

	public static ArrayList<Verse> searchResults;

	public final static String[] shahhd = { "تك", "خر", "لا", "عد", "تث", "يش",
			"قض", "را", "صم ١", "صم ٢", "مل ١", "مل ٢", "أخ ١", "أخ ٢", "عز ",
			"نح", "طو", "يهو", "أس", "أي", "مز", "أم", "جا", "نش", "حك", "سي",
			"إش", "إر", "مرا", "با", "حز", "دا", "هو", "يؤ", "عا",
			"عو", "يون", "مي", "نا", "حب", "صف", "حج", "زك", "ملا", "مك ١",
			"مك ٢", "مت", "مر", "لو", "يو", "أع", "رو", "كو ١", "كو ٢", "غل",
			"أف", "في", "كو", "تس ١", "تس ٢", "تي ١", "تي ٢", "تي", "فل", "عب",
			"يع", "بط ١", "بط ٢", "يو ١", "يو ٢", "يو ٣", "يه", "رؤ" };
	public final static ArrayList<String> menuItems = new ArrayList<String>() {
		{
			add("الآيات المُفضلة");
			add("عهد جديد");
			add("عهد قديم");
			add("بحث متقدم");
			add("بحث بالشاهد");
			add("تابعنا على فيس بوك");
			add("تواصل مع المُطور");
		}
	};
	public final static String[] chapterNames = { "الأَوَّلُ", "الثَّانِي",
			"الثَّالِثُ", "الرَّابعُ", "الْخَامِسُ", "السَّادِسُ", "السَّابعُ",
			"الثَّامِنُ", "التَّاسِعُ", "الْعَاشِرُ", "الْحَادِي عَشَرَ",
			"الثَّانِي عَشَرَ", "الثَّالِثُ عَشَرَ", "الرَّابعُ عَشَرَ",
			"الْخَامِسُ عَشَرَ", "السَّادِسُ عَشَرَ", "السَّابعُ عَشَرَ",
			"الثَّامِنُ عَشَرَ", "التَّاسِعُ عَشَرَ", "الْعِشْرُونَ",
			"الْحَادِي وَالْعِشْرُونَ", "الثَّانِي وَالْعِشْرُونَ",
			"الثَّالِثُ وَالْعِشْرُونَ", "الرَّابعُ وَالْعِشرُونَ",
			"الْخَامِسُ وَالْعِشْرُونَ", "السَّادِسُ وَالْعِشْرُونَ",
			"السَّابعُ وَالْعِشْرُونَ", "الثَّامِنُ وَالْعِشْرُونَ",
			"التَاسِعُ وَالْعِشْرُونَ", "الثَّلاَثُونَ",
			"الْحَادِي وَالثَّلاَثُونَ", "الثَّانِي وَالثَّلاَثُونَ",
			"الثَّالِثُ وَالثَّلاَثُونَ", "الرَّابِعُ وَالثَّلاَثُونَ",
			"الْخَامِسُ وَالثَّلاَثُونَ", "السَّادِسُ وَالثَّلاَثُونَ",
			"السَّابعُ وَالثَّلاَثُونَ", "الثَّامِنُ وَالثَّلاَثُونَ",
			"التَّاسِعُ وَالثَّلاَثُونَ", "ألأَرْبَعُونَ",
			"الْحَادِي والأَرْبَعُونَ", "الثَّانِي وَالأَرْبَعُونَ",
			"الثَّالِثُ والأَرْبَعُونَ", "الرَّابعُ والأرْبَعُونَ",
			"الْخَامِسُ والأَرْبَعُونَ", "السَّادِسُ والأَرْبَعُونَ",
			"السَّابعُ والأَرْبَعُونَ", "الثَّامِنُ والأرْبَعُونَ",
			"التَّاسِعُ والأرْبَعُونَ", "الْخَمْسُونَ",
			"الْحَادِي والْخَمْسُونَ", "الثَّانِي وَالْخَمْسُونَ",
			"الثَّالِثُ وَالْخَمْسُونَ", "الرَّابعُ وَالْخَمْسُونَ",
			"الْخَامِسُ وَالْخَمْسُونَ", "السَّادِسُ وَالْخَمْسُونَ",
			"السَّابعُ وَالْخَمْسُونَ", "الثَّامِنُ وَالْخَمْسُونَ",
			"التَّاسِعُ وَالْخَمْسُونَ", "السِّتُّونَ",
			"الْحَادِي وَالسِّتُّونَ", "الثَّانِي والسِّتُّونَ",
			"الثَّالِثُ وَالسِّتُّونَ", "الرَّابعُ وَالسِّتُّونَ",
			"الْخَامِسُ وَالسِّتُّونَ", "السَّادِسُ وَالسِّتُّونَ", };

	public static String getBibleChapterStr(int bid, int cid) {
		if (bid == 20)
			return bibleNames[bid] + " - " + " مَزْمُورُُ " + getArabicNum(cid);
		return bibleNames[bid] + " - " + "الأصحَاحُ " + chapterNames[cid - 1];

	};

	public final static short[] bibleLengths = {
			// old testament
			50, 40, 27, 36, 34, 24, 21, 4, 31, 24, 22, 25, 29, 36, 10, 13, 14,
			16, 10, 42, 151, 31, 12, 8, 19, 51, 66, 52, 5, 6, 48, 12, 14, 3, 9,
			1, 4, 7, 3, 3, 3, 2, 14, 4, 16, 15,
			// new testament
			28, 16, 24, 21, 28, 16, 16, 13, 6, 6, 4, 4, 5, 3, 6, 4, 3, 1, 13,
			5, 5, 3, 5, 1, 1, 1, 22

	};
	public final static String[] bibleNewTestNames = { "انجيل متى",
			"انجيل مرقس", "انجيل لوقا", "انجيل يوحنا", "أعمال الرسل",
			"الرسالة إلى رومية", "الرسالة الأولى إلى أهل كورنثوس",
			"الرسالة الثانية إلى أهل كورنثوس", "الرساله الى غلاطية",
			"الرساله ألى أفسس", "الرسالة إلي فيلبي", "الرسالة الي كولوسي",
			"الرسالة الأولى إلى أهل تسالونيكي",
			"الرسالة الثانية إلى أهل تسالونيكي", "الرسالة الاولى إلى تيموثاوس",
			"الرسالة الثانيه إلى تيموثاوس", "الرسالة إلى تيطس",
			"الرسالة إلى فليمون", "الرسالة إلى العبرانيين", "رسالة يعقوب",
			"رساله بطرس الأولى", "رسالة بطرس الثانية", "رسالة يوحنا الأولى",
			"رسالة يوحنا الثانية", "رسالة يوحنا الثالثة", "رسالة يهوذا",
			"رؤيا يوحنا اللاهوتى" };
	public final static String[] bibleOldTestNames = { "التكوين", "الخروج",
			"اللاويين", "العدد", "التثنية", "يشوع", "القضاة", "راعوث",
			"صموئيل الأول", "صموئيل الثاني", "الملوك الأول", "الملوك الثاني",
			"أخبار الأيام الأول", "أخبار الأيام الثاني", "عزرا", "نحميا", "طوبيت",
			"يهوديت", "أستير", "أيوب", "المزامير", "الأمثال", "الجامعة",
			"نشيد الأنشاد", "الحكمة", "يشوع بن سيراخ", "إشعياء", "إرميا",
			"مراثي ارميا", "باروخ", "حزقيال", "دانيال", "هوشع", "يوئيل",
			"عاموس", "عوبديا", "يونان", "ميخا", "ناحوم", "حبقوق", "صفنيا",
			"حجي", "زكريا", "ملاخي", "المكابيين الأول", "المكابيين الثاني" };
	public final static String[] bibleNames = { "التكوين", "الخروج",
			"اللاويين", "العدد", "التثنية", "يشوع", "القضاة", "راعوث",
			"صموئيل الأول", "صموئيل الثاني", "الملوك الأول", "الملوك الثاني",
			"أخبار الأيام الأول", "أخبار الأيام الثاني", "عزرا", "نحميا", "طوبيت",
			"يهوديت", "أستير", "أيوب", "المزامير", "الأمثال", "الجامعة",
			"نشيد الأنشاد", "الحكمة", "يشوع بن سيراخ", "إشعياء", "إرميا",
			"مراثي ارميا", "باروخ", "حزقيال", "دانيال", "هوشع", "يوئيل",
			"عاموس", "عوبديا", "يونان", "ميخا", "ناحوم", "حبقوق", "صفنيا",
			"حجي", "زكريا", "ملاخي", "المكابيين الأول", "المكابيين الثاني",
			"انجيل متى", "انجيل مرقس", "انجيل لوقا", "انجيل يوحنا",
			"أعمال الرسل", "الرسالة إلى رومية",
			"الرسالة الأولى إلى أهل كورنثوس",
			"الرسالة الثانية إلى أهل كورنثوس", "الرساله الى غلاطية",
			"الرساله ألى أفسس", "الرسالة إلي فيلبي", "الرسالة الي كولوسي",
			"الرسالة الأولى إلى أهل تسالونيكي",
			"الرسالة الثانية إلى أهل تسالونيكي", "الرسالة الاولى إلى تيموثاوس",
			"الرسالة الثانيه إلى تيموثاوس", "الرسالة إلى تيطس",
			"الرسالة إلى فليمون", "الرسالة إلى العبرانيين", "رسالة يعقوب",
			"رساله بطرس الأولى", "رسالة بطرس الثانية", "رسالة يوحنا الأولى",
			"رسالة يوحنا الثانية", "رسالة يوحنا الثالثة", "رسالة يهوذا",
			"رؤيا يوحنا اللاهوتى" };

	public static boolean isValidChapterNum(String str) {
		try {
			int n = Integer.parseInt(str);
			return n < 152 && n > 0;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isValidVerseNum(String str) {
		try {
			int n = Integer.parseInt(str);
			return n < 180 && n > 0;
		} catch (Exception e) {
			return false;
		}
	}

}
