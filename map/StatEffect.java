public enum StatEffect
{
    BURN1(1), HEAL1(1), MPHEAL1(1), BLEED1(1), STUN1(1), SLOW1(1), POISON1(1),
    BURN2(2), HEAL2(2), MPHEAL2(2), BLEED2(2), STUN2(2), SLOW2(2), POISON2(2),
    BURN3(3), HEAL3(3), MPHEAL3(3), BLEED3(3), STUN3(3), SLOW3(3), POISON3(3),
    BURN4(4), HEAL4(4), MPHEAL4(4), BLEED4(4), STUN4(4), SLOW4(4), POISON4(4),
    BURN5(5), HEAL5(5), MPHEAL5(5), BLEED5(5), STUN5(5), SLOW5(5), POISON5(5),
    SUPERSPEED(0);
    
    private final int lvl;
    StatEffect(int l)
    {
	lvl = l;
    }
}
